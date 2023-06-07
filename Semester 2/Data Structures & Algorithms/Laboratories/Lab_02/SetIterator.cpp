#include "SetIterator.h"
#include "Set.h"
#include <exception>

using namespace std;

///BC=WC=AV= Theta(1)
SetIterator::SetIterator(const Set& m) : set(m)
{
	current = nullptr;
	first();
}

///BC=WC=AV= Theta(1)
void SetIterator::previous()
{
	if (!valid()) {
		throw exception();
	}
	current = current->prev;
}

///BC=WC=AV= Theta(1)
void SetIterator::first() {
	current = set.head;
	return;
}

///BC=WC=AV= Theta(1)
void SetIterator::next() {
	if (!valid()) {
		throw exception();
	}
	current = current->next;
}

///BC=WC=AV= Theta(1)
TElem SetIterator::getCurrent()
{
	if (!valid()) {
		throw exception();
	}
	return current->info;
}

///BC=WC=AV= Theta(1)
bool SetIterator::valid() const {
	if (set.size() == 0 || current == nullptr)
		return false;
	return true;
}



