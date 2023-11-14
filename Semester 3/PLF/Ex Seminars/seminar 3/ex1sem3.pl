%given a list of numbers & sublists of number, substitute each sublist in which the sum of the elements is odd with the first element 
%of that sublist; Ex: [1,2,[2,4],7,3,[4,6,7],[1],8,10,[2,3]] => [1,2,[2,4],7,3,4,1,8,10,2]
%sum(L-list, S-the sum), flow model: (i,o), (i,i)

%base case
sum([],0).

sum([H|T],NewS):- 
   sum(T,S),
   NewS is S+H.

solve([],[]).

solve([H|T],[NewH|NewT]):-
     islist(H),
     sum(H,S),
     mod(S,2) =:= 1,
     !,
     H = [NewH,_],
     solve(T, NewT).

solve([H|T], [H|NewT]):-
     solve(T, NewT).     

