#pragma once

//DO NOT CHANGE THIS PART
typedef int TElem;
#define NULL_TELEM 0


class Matrix {

private:
	struct Node {
		struct Triple
		{
			int row;
			int col;
			TElem value;
			bool operator==(const Triple& other) const {
				return row == other.row && col == other.col && value == other.value;
			}
		}data;
		Node* left;
		Node* right;
	};

	int nrCols, nrRows;
	Node* root;

	static bool compareTriple(const Node::Triple& t1, const Node::Triple& t2) {
		if (t1.row < t2.row) return true;
		else if (t1.row == t2.row) return t1.col < t2.col;
		else return false;
	}

	Node* find(Node* node, const Node::Triple& data) const {
		if (node == nullptr || (node->data.row == data.row && node->data.col == data.col)) {
			return node;
		}

		if (compareTriple(data, node->data)) {
			return find(node->left, data);
		}
		else {
			return find(node->right, data);
		}
	}

	Node* insert(Node* node, const Node::Triple& data) {
		if (node == nullptr) {
			node = new Node;
			node->data = data;
			node->left = node->right = nullptr;
		}
		else if (compareTriple(data, node->data)) {
			node->left = insert(node->left, data);
		}
		else {
			node->right = insert(node->right, data);
		}
		return node;
	}


public:

	int numberOfNonZeroElems(int col) const;
	//constructor
	Matrix(int nrLines, int nrCols);

	//returns the number of lines
	int nrLines() const;

	//returns the number of columns
	int nrColumns() const;

	//returns the element from line i and column j (indexing starts from 0)
	//throws exception if (i,j) is not a valid position in the Matrix
	TElem element(int i, int j) const;

	//modifies the value from line i and column j
	//returns the previous value from the position
	//throws exception if (i,j) is not a valid position in the Matrix
	TElem modify(int i, int j, TElem e);

};
