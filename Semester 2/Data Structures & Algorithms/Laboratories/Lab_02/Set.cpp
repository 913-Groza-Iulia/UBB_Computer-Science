#include "Set.h"
#include "SetITerator.h"

///BC=WC=AV= Theta(1)
Set::Set() {
	head = nullptr;
	tail = nullptr;
	length = 0;
}

///BC=WC=AV= Theta(1)
bool Set::add(TElem elem) {

	if (search(elem)==true) {
		return false;
	}
	Node* new_node = new Node{elem, nullptr, nullptr};
	if (length == 0) {
		head = new_node;
		tail = new_node;
		length++;
	}
	else {
		// Add the new node to the end of the set
		tail->next = new_node;
		new_node->prev = tail;
		tail = new_node;
		length++;
	}
	return true;
}

///BC=Theta(1)
///WC=AV= Theta(n)
bool Set::remove(TElem elem) {
	Node* current = head;
	while (current != nullptr && current->info != elem) {
		current = current->next;
	}
	// If the value was not found, return false
	if (current == nullptr) {
		return false;
	}
	if (current == head) {
		head = current->next;
	}
	else {
		current->prev->next = current->next;
	}

	if (current == tail) {
		tail = current->prev;
	}
	else {
		current->next->prev = current->prev;
	}

	delete current;
	length--;
	return true;
}


///BC=Theta(1)
///WC=AV= Theta(n)
bool Set::search(TElem elem) const {
	Node* current = head;
	while (current != nullptr) {
		if (current->info == elem) {
			return true;
		}
		current = current->next;
	}
	return false;
}

///BC=WC=AV= Theta(1)
int Set::size() const {
	return length;
}

///BC=WC=AV= Theta(1)
bool Set::isEmpty() const {
	return length == 0;
}

///BC=WC=AV= Theta(n)
Set::~Set() {
	Node* current = head;
	while (current != nullptr) {
		Node* x = current;
		current = current->next;
		delete x;
	}
}


SetIterator Set::iterator() const {
	return SetIterator(*this);
}


