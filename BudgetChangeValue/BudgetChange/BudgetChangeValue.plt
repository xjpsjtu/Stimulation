set terminal postscript enhanced
set term epscairo lw 2 font "Times-Roman, 26" size 5,4
set size ratio 0.8
set xrange [0:20000]
set yrange [0:400]
set xtics 5000
set ytics 100
set xlabel "Budget" font "Times-Roman,26"
set ylabel "Value" font "Times-Roman,26"
set key at 20000,250
set key box
set output "G:\\workspace2\\stimulation\\BudgetChangeValue\\BudgetChange\\BudgetChangeV.eps"
plot "G:\\workspace2\\stimulation\\BudgetChangeValue\\BudgetChange\\BudgetChange&Random2.txt" u 1:4 w lp pt 5 lw 2 ps 1.2 t "TDM", '' u 1:3 w lp pt 7 lw 2 ps 1.2 t "OPT", '' u 1:2 w lp pt 9 lw 2 ps 1.2 t "Random"
set output
 