#include "SetIterator.h"
#include "Set.h"
#include <iostream>
#include <exception>

using namespace std;

// The BC = WC = AC = Theta(1)
SetIterator::SetIterator(const Set& m) : set(m)
{
	c1 = 0;
	c2 = 0;
	first();
}

//BC = Theta(1)
//WC = AC = Theta(n)
void SetIterator::first() {
	c1 = 0;
	for (int i = 0; i < set.max - set.min + 1; i++) {
		if (set.elems[i] == true) {
			return;
		}
		c1++;
	}
}

//BC = Theta(1)
//WC = AC = Theta(n)
void SetIterator::next() {
	if (!valid()) {
		throw exception();
	}
	for (int i = c1 + 1; i <= set.max - set.min + 1; i++) {
		if (set.elems[i] == true) {
			c1 = i;
			return;
		}
		c1++;
	}
}

// The BC = WC = AC = Theta(1)
TElem SetIterator::getCurrent()
{
	if (!valid()) throw exception();
	return c1 + set.min;
}

// The BC = WC = AC = Theta(1)
bool SetIterator::valid() const {
	if ((c1 < 0 || c1 >= set.max - set.min + 1) || set.size() == 0)
	{
		return false;
	}
	return true;
}



