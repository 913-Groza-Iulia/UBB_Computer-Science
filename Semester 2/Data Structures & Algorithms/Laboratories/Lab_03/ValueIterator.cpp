#include "ValueIterator.h"
#include "SortedMultiMap.h"
#include <exception>
using namespace std;


ValueIterator::ValueIterator(const SortedMultiMap& d, TKey c) : map(d), key(c){
	key = map.head;
	while (key!=-1 && map.keys[key] != c)
	{
		key = map.next[key];
	}
	if (key == -1)
	{
		validIterator = false;
	}
	else
	{
		validIterator = true;
	}
	current = map.values[key].head;
}

void ValueIterator::first() {
	current = map.values[key].head;
}

void ValueIterator::next(){
	if (!valid())
		throw exception();
	else
	{
		if (map.values[key].next[current] != -1)
		    current = map.values[key].next[current];
	}
}

bool ValueIterator::valid() const{

	return validIterator && current != -1;
}

TValue ValueIterator::getCurrent() const{
	if (!valid())
		throw exception();
	TValue currentValue=map.values[key].value[current];
	return currentValue;
}


