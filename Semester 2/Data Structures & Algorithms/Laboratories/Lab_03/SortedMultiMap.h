#pragma once
//DO NOT INCLUDE SMMITERATOR

//DO NOT CHANGE THIS PART

#include <vector>
#include <utility>
typedef int TKey;
typedef int TValue;
typedef std::pair<TKey, TValue> TElem;
#define NULL_TVALUE -111111
#define NULL_TELEM pair<TKey, TValue>(-111111, -111111);

using namespace std;

class SMMIterator;
class ValueIterator;
typedef bool(*Relation)(TKey, TKey);

class SortedMultiMap {
	friend class SMMIterator;
    friend class ValueIterator;
    private:

        struct SLLA
        {
            int capacity;
            int head;
            int* next;
            TValue* value;
            int firstFree;
            int length;

            SLLA()
            {
                capacity = 100;
                head = -1;
                next = new int[capacity];
                value = new TValue[capacity];
                firstFree = 0;
                length = 0;
                for (int i = 0; i < capacity; i++)
                {
                    next[i] = -1;
                    value[i] = NULL_TVALUE;
                }
            }

            void Resize()
            {
                int newCapacity = capacity * 2;
                int* newNext = new int[newCapacity];
                TValue* newValue = new TValue[newCapacity];

                for (int i = 0; i < capacity; i++)
                {
                    newNext[i] = next[i];
                    newValue[i] = value[i];
                }

                delete[] next;
                delete[] value;

                capacity *= 2;
                next = newNext;
                value = newValue;
                capacity = newCapacity;
            }

            ~SLLA()
            {
                delete[] next;
                delete[] value;
            }
        };

        Relation inboundRelation;
        int firstFree;
        int capacity;
        TKey* keys;
        SLLA* values;
        int* next;
        int head;
        int keyLength;
        int totalLength;

    public:

    // constructor
    SortedMultiMap(Relation r);

	//adds a new key value pair to the sorted multi map
    void add(TKey c, TValue v);


    void Resize();

	//returns the values belonging to a given key
    vector<TValue> search(TKey c) const;

	//removes a key value pair from the sorted multimap
	//returns true if the pair was removed (it was part of the multimap), false if nothing is removed
    bool remove(TKey c, TValue v);

    //returns the number of key-value pairs from the sorted multimap
    int size() const;

    //verifies if the sorted multi map is empty
    bool isEmpty() const;

    // returns an iterator for the sorted multimap. The iterator will returns the pairs as required by the relation (given to the constructor)	
    SMMIterator iterator() const;

    ValueIterator iterator1(TKey key) const;

    // destructor
    ~SortedMultiMap();
};
