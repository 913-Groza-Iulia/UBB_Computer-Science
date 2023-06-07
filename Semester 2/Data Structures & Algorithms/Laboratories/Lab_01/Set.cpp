#include "Set.h"
#include "SetITerator.h"
#include <exception>
#include <iostream>

using namespace std;

//BC= WC = AC = Theta(1)
Set::Set() {
	this->elems = new bool[1];
	this->min = 0;
	this->max = 0;
	this->elems[0] = false;
}

//BC= AC= WC= Theta(1)
void Set::empty()
{
	delete[] this->elems;
	this->elems = new bool[1];
	this->min = 0;
	this->max = 0;
	this->elems[0] = false;
}

//BC =WC = AC = Theta(n)
void Set::resizeMin(int newMin)
{
	bool* newElems = new bool[max - newMin + 1];
	for (int i = 0; i < max - newMin + 1; i++) {
		newElems[i] = false;
	}
	for (int i = min; i <= max; i++) {
		newElems[i - newMin] = elems[i];
	}
	delete[] elems;
	elems = newElems;
	this->min = newMin;
	elems[0] = true;
}

//BC =WC = AC = Theta(n)
void Set::resizeMax(int newMax) {
	bool* newElems = new bool[newMax - min + 1];
	for (int i = 0; i < newMax - min + 1; i++) {
		newElems[i] = false;
	}
	for (int i = 0; i < max - min + 1; i++) {
		newElems[i] = elems[i];
	}
	newElems[newMax - min] = true;
	delete[] elems;
	elems = newElems;
	this->max = newMax;
}

//BC = WC = AC = Theta(n)
bool Set::add(TElem elem) {
	if (elem >= min && elem <= max) {
		int newPos = elem - min;
		if (elems[newPos] == true) {
			return false;
		}
		elems[newPos] = true;
	}
	else {
		if (elem < min) {
			resizeMin(elem);
		}
		else if (elem > max) {
			resizeMax(elem);
		}
	}
	return true;
}

//BC = WC = AC = Theta(1)
bool Set::remove(TElem elem) {
	if (elem < min || elem > max) return false;
	if (elems[elem - min] == false) return false;
	elems[elem - min] = false;
	return true;
}
//BC = WC = AC = Theta(1)
bool Set::search(TElem elem) const {
	if (elem < min || elem > max)
		return false;
	return elems[elem - min];
}

//BC = Theta(1)
//WC = AC = Theta(n)
int Set::size() const {
	int size = 0;
	for (int i = 0; i < max - min + 1; i++)
	{
		if (elems[i] == true) {
			size++;
		}
	}
	return size;
}
//BC = WC = AC = Theta(1)
bool Set::isEmpty() const {
	return size() == 0;
}

//BC = WC = AC = Theta(1)
Set::~Set() {
	delete[] elems;
}

//BC = WC = AC = Theta(1)
SetIterator Set::iterator() const {
	return SetIterator(*this);
}