set terminal postscript enhanced
set term epscairo lw 2 font "Times-Roman, 20" size 5,4
set size ratio 0.8
set xrange [6000:15000]
set yrange [0.6:1]
set xtics 2000
set ytics 0.1
set xlabel "n" font "Times-Roman,26"
set ylabel "Select Ratio" font "Times-Roman,26"
set key at 15000,1
set key box
set key width 1
set output "C:\\Users\\xjp\\git\\Stimulation\\BudgetChangeRatio\\RatioBudget.eps"
plot  "C:\\Users\\xjp\\git\\Stimulation\\BudgetChangeRatio\\BudgetChangeRatio200.txt" u 1:2 w lp pt 7 lw 2 ps 1 t "n = 200","C:\\Users\\xjp\\git\\Stimulation\\BudgetChangeRatio\\BudgetChangeRatio250.txt" u 1:2 w lp pt 7 lw 2 ps 1 t "n = 250","C:\\Users\\xjp\\git\\Stimulation\\BudgetChangeRatio\\BudgetChangeRatio300.txt" u 1:2 w lp pt 7 lw 2 ps 1 t "n = 300","C:\\Users\\xjp\\git\\Stimulation\\BudgetChangeRatio\\BudgetChangeRatio400.txt" u 1:2 w lp pt 7 lw 2 ps 1 t "n = 400" 
set output
 