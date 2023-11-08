#include "Matrix.h"
#include <exception>
using namespace std;


Matrix::Matrix(int nrLines, int nrCols) {
	this->nrRows = nrLines;
	this->nrCols = nrCols;
	root = nullptr;
}

int Matrix::nrLines() const {
	return nrRows;
}

int Matrix::nrColumns() const {
	return nrCols;
}

TElem Matrix::element(int i, int j) const {
	if (i < 0 || i >= nrRows || j < 0 || j >= nrCols)
		throw exception();

	Node::Triple t = { i, j, NULL_TELEM };
	Node* found = find(root, t);
	if (found != nullptr) {
		return found->data.value;
	}
	return NULL_TELEM;
}

TElem Matrix::modify(int i, int j, TElem e) {
	if (i < 0 || i >= nrRows || j < 0 || j >= nrCols)
		throw exception();

	Node::Triple t = { i, j, e };
	Node* found = find(root, t);
	if (found != nullptr) {
		TElem oldVal = found->data.value;
		found->data.value = e;
		return oldVal;
	}
	root = insert(root, t);
	return NULL_TELEM;
}

int Matrix::numberOfNonZeroElems(int col) const
{
	if (col < 0 || col >= nrCols)
		throw exception();

	int count = 0;
	for (int i = 0; i < nrRows; i++)
	{
		TElem value = element(i, col);
		if (value != NULL_TELEM)
		{
			count++;
		}
	}
	return count;
}

