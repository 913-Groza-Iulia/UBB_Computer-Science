#include "SMMIterator.h"
#include "SortedMultiMap.h"
#include "ValueIterator.h"
#include <iostream>
#include <vector>
#include <exception>
using namespace std;

SortedMultiMap::SortedMultiMap(Relation r) {
	inboundRelation = r;
	capacity = 10;
	keys = new TKey[capacity];
	values = new SLLA[capacity];
	next = new int[capacity];
	head = -1;
	keyLength = 0;
	firstFree = 0;
	totalLength = 0;

	for (int i = 0; i < capacity; i++)
	{
		next[i] = -1;
		keys[i] = NULL_TVALUE;
	}
}

void SortedMultiMap::Resize()
{
	capacity *= 2;
	TKey* newKeys = new TKey[capacity];
	SLLA* newValues = new SLLA[capacity];
	int* newNext = new int[capacity];
	for (int i = 0; i < keyLength; i++)
	{
		newKeys[i] = keys[i];
		for (int j = 0; j < values[i].length; j++) {
			newValues[i].value[j] = values[i].value[j];
			newValues[i].next[j] = values[i].next[j];
		}
		newValues[i].firstFree = values[i].firstFree;
		newValues[i].head = values[i].head;
		newValues[i].length = values[i].length;
		newNext[i] = next[i];
	}
	for (int i = keyLength; i < capacity; i++) {
		newNext[i] = -1;
		newKeys[i] = NULL_TVALUE;
	}
	delete[] keys;
	delete[] values;
	delete[] next;
	keys = newKeys;
	values = newValues;
	next = newNext;
	firstFree = keyLength;
}

void SortedMultiMap::add(TKey c, TValue v) {
	if (keyLength == capacity)
		Resize();

	if (head == -1 || !inboundRelation(keys[head], c)) {
		keys[firstFree] = c; //punem c pe prima pozitie libera din keys
		int firstFreeInSLLA = values[firstFree].firstFree; //first free din slla
		values[firstFree].value[firstFreeInSLLA] = v; //punem v pe prima pozitie libera din slla
		values[firstFree].length++;
		//daca e goala lista 
		values[firstFree].head = firstFreeInSLLA; 
		values[firstFree].firstFree++;
		//daca pun cheia pe prima pozitie 
		next[firstFree] = head;//next de cheie e head 
		head = firstFree;
		//
		keyLength++;
		totalLength++;
		for (int i = 0; i <= keyLength; i++)
			if (keys[i] == NULL_TVALUE) {
				firstFree = i;
				break;
			}
	}
	else
	{
		int current = head;
		while (next[current] != -1 && inboundRelation(keys[next[current]], c))//Iterate through the map until finding the 
			//correct position to insert the new key.
			current = next[current];

		if (keys[current] == c) //if the key already exists
		{
			if (values[current].length == values[current].capacity)
				values[current].Resize();

			values[current].value[values[current].firstFree] = v;//in array ul din slla, pe pozitia firstfree pun v
			values[current].length++;
			totalLength++;
			int currentValue = values[current].head;//head ul de la slla ul curent
			while (values[current].next[currentValue] != -1) ///elem curent din slla are next
				currentValue = values[current].next[currentValue];//currentValue va fi next ul lui
			values[current].next[currentValue] = values[current].firstFree;//cand iese din while, a gasit next ul cu -1 si il pune ca firstfree
			for (int i = 0; i <= values[current].length; i++)
				if (values[current].value[i] == NULL_TVALUE) {
					values[current].firstFree = i;
					break;
				}
				
		}
		else //daca cheia nu exista
		{
			keys[firstFree] = c;
			int firstFreeInSLLA = values[firstFree].firstFree;
			values[firstFree].value[firstFreeInSLLA] = v;
			values[firstFree].length++;
			values[firstFree].head = firstFreeInSLLA;
			values[firstFree].firstFree++;
			next[firstFree] = next[current];
			next[current] = firstFree;
			keyLength++;
			totalLength++;
			for (int i = 0; i <= keyLength; i++)
				if (keys[i] == NULL_TVALUE) {
					firstFree = i;
					break;
				}
		}
	}
}

vector<TValue> SortedMultiMap::search(TKey c) const {
	vector<TValue> result;
	int current = head;
	while (current != -1)
	{
		if (keys[current] == c) ///cheia primului elem din map
		{
			for (int i = 0; i < values[current].length; i++)
			{
				result.push_back(values[current].value[i]);
			}
			break;
		}
		current = next[current];
	}
	return result;
}

bool SortedMultiMap::remove(TKey c, TValue v) {
	int current = head;
	int previous = -1;
	while (current != -1 && keys[current] != c) { /// cat timp n am gasit cheia pe care sa o remove
		previous = current;
		current = next[current];
	}

	if (current == -1)
		return false;

	int valueIndex = -1;
	for (int i = values[current].head; i != -1; i = values[current].next[i])///iterez printre values slla ului
		if (values[current].value[i] == v)//daca gasim value ul de la cheia respectiva
		{
			valueIndex = i;
			break;
		}

	if (valueIndex == -1)
		return false;

	if (values[current].head == valueIndex) { //daca v este head-ul slla ului
		values[current].head = values[current].next[valueIndex];
	} 
	///efectiv the removal
	values[current].value[valueIndex] = NULL_TVALUE;
	values[current].next[valueIndex] = -1;
	values[current].firstFree = valueIndex;
	values[current].length--;
	totalLength--;

	if (values[current].length == 0) {///daca slla ul de values asociat key ului e 0 after removal
		// remove key from the map if the list is empty
		if (current == head) {
			head = next[current];
		}
		else {
			next[previous] = next[current]; // skip the current node
		}
		keys[current] = NULL_TVALUE; // elimina cheia
		next[current] = firstFree;
		firstFree = current;
		keyLength--;
	}

	return true;
}


int SortedMultiMap::size() const {
	return totalLength;
}

bool SortedMultiMap::isEmpty() const {
	if (keyLength == 0)
		return true;
	return false;
}

SMMIterator SortedMultiMap::iterator() const {
	return SMMIterator(*this);
}

ValueIterator SortedMultiMap::iterator1(TKey key) const {
	return ValueIterator(*this,key);
}

SortedMultiMap::~SortedMultiMap() {
	delete[] keys;
	delete[] values;
	delete[] next;
}
