%a.For a list of integer number, write a predicate to add in list after 1-st, 3-rd, 7-th, 15-th element a given value e.
%addElem(L-list,E=elem to add, P-positions, R-result list)    
addElem([],_,_,[]).

addElem([H|T],E,P,[H,E|R]):-
    (P is 1 ; P is 3 ; P is 7 ; P is 15),
    !,
    P1 is P + 1,
    addElem(T,E,P1,R).

addElem([H|T],E,P,[H|R]):-
    P1 is P + 1,
    addElem(T,E,P1,R).

 
%b. For a heterogeneous list, formed from integer numbers and list of numbers; add in every sublist after 1-st, 
%3-rd, 7-th, 15-th element the value found before the sublist in the heterogenous list. The list has the particularity 
%that starts with a number and there arent two consecutive elements lists.

heterList([],_,[]).

heterList([H|T],E,[RA|R]):-
    is_list(H),
    !,
    addElem(H,E,1,RA),
    heterList(T,E,R).

heterList([H|T],_,[H|R]):- 
    heterList(T,H,R).

