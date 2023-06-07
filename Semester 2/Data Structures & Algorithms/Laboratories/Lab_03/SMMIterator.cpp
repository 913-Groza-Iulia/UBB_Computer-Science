#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include <exception>
using namespace std;


SMMIterator::SMMIterator(const SortedMultiMap& d) : map(d){
	currentKeyIndex = map.head; //prima cheie
	currentElementIndex = map.values[map.head].head;//head ul de la primul slla de la prima cheie
}

void SMMIterator::first() {
	currentKeyIndex = map.head;
	currentElementIndex = map.values[map.head].head;
}

void SMMIterator::next(){
	if (!valid())
		throw exception();
	if (map.values[currentKeyIndex].next[currentElementIndex] != -1) ///elem curent din slla are next
		currentElementIndex = map.values[currentKeyIndex].next[currentElementIndex];
	else {
		currentKeyIndex = map.next[currentKeyIndex]; //daca nu are next, merge la urm cheie
		currentElementIndex = map.values[currentKeyIndex].head;// head ul de la slla ul nou
	}
}

bool SMMIterator::valid() const{
	if (currentKeyIndex != -1) 
		return true;
	return false;
}

TElem SMMIterator::getCurrent() const{
	if (!valid())
		throw exception();
	TKey currentKey = map.keys[currentKeyIndex];
	TValue currentValue = map.values[currentKeyIndex].value[currentElementIndex];
	TElem currentElement;
	currentElement.first = currentKey;
	currentElement.second = currentValue;
	return currentElement;
}


