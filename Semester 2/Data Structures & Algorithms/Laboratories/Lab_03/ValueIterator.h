#pragma once

#include "SortedMultiMap.h"


class ValueIterator {

	friend class SortedMultiMap;

private:
	//DO NOT CHANGE THIS PART
	const SortedMultiMap& map;
	ValueIterator(const SortedMultiMap& map, TKey c);


	int current;
	TKey key;
	bool validIterator;
	

public:
	void first();
	void next();
	bool valid() const;
	TValue getCurrent() const;
};

