set terminal postscript enhanced
set term epscairo lw 2 font "Times-Roman, 20" size 5,4
set size ratio 0.8
set xrange [200:500]
set yrange [0.6:1]
set xtics 50
set ytics 0.1
set xlabel "n" font "Times-Roman,26"
set ylabel "Select Ratio" font "Times-Roman,26"
set key at 500,1
set key box
set key width 1
set output "C:\\Users\\xjp\\git\\Stimulation\\NumChangeRatio\\AgentRatio.eps"
plot  "C:\\Users\\xjp\\git\\Stimulation\\NumChangeRatio\\NumChangeRatio15000.txt" u 1:2 w lp pt 7 lw 2 ps 1 t "Budget = 15000","C:\\Users\\xjp\\git\\Stimulation\\NumChangeRatio\\NumChangeRatio10000.txt" u 1:2 w lp pt 7 lw 2 ps 1 t "Budget = 10000","C:\\Users\\xjp\\git\\Stimulation\\NumChangeRatio\\NumChangeRatio8000.txt" u 1:2 w lp pt 7 lw 2 ps 1 t "Budget = 8000","C:\\Users\\xjp\\git\\Stimulation\\NumChangeRatio\\NumChangeRatio6000.txt" u 1:2 w lp pt 7 lw 2 ps 1 t "Budget = 6000" 
set output
 