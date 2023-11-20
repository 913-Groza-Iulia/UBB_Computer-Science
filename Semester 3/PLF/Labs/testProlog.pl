%define a predicate that merges 2 sorted lists of integers numbers and puts just one value if there is duplicate values
%ex L1:[1,2,3,5,7], L2: [2,4,6,8] => [1,2,3,4,5,6,7,8]

%mergeLists(L1,L2,R):
%= L1 if L2 is empty
%= L2 if L1 is empty
%= {l1} + mergeLists(l2l3...ln, L1L2..Ln, l1) if l1<L1
%= {L1} + mergeLists(l1l2l3...ln, L2..Ln, L1) if l1>L1
%= mergeLists(l2l3...ln, L2L3..Ln, l1) if l1=L1

mergeLists(L1, [], L1) :- !.
mergeLists([], L2, L2):- !.

mergeLists([H1|T1], [H2|T2], [H1|R]):-
    H1<H2,
    mergeLists(T1, [H2|T2], R).

mergeLists([H1|T1], [H2|T2], [H2|R]):-
    H1>H2,
    mergeLists([H1|T1], T2, R).

mergeLists([H1|T1], [H2|T2], [H1|R]):-
   H1=:=H2,
   mergeLists(T1, T2, R).

