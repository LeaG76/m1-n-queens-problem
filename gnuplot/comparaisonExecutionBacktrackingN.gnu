set terminal postscript eps enhanced color 15
set encoding utf8
set terminal png enhanced font 'Roboto,'
set output "../pictures/comparaisonExecutionBacktrackingN.png"
set title "Temps d'exécution de la méthode de backtracking en fonction de n\npour la résolution du problème des N Reines"
set xtics font ",10"
set ytics font ",10"
set xlabel 'Taille de l''échiquier / nombre de reines : n' font ",10"
set ylabel 'Temps d''exécution en ms' font ",10"
set key font ",10" top left samplen 5 spacing 1
set style line 1 lt rgb "red" lw 1
plot '../data/comparaisonExecutionBacktrackingN.dat' title 'Méthode Backtracking' with linesp ls 1 pt 1