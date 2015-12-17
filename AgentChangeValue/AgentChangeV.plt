set terminal postscript enhanced
set term epscairo lw 2 font "Times-Roman, 26" size 5,4
set size ratio 0.8
set xrange [0:300]
set yrange [0:800]
set xtics 100
set ytics 200
set xlabel "Agent" font "Times-Roman,26"
set ylabel "Value" font "Times-Roman,26"
set key at 160,800
set key box
set key width 1
set output "G:\\workspace2\\stimulation\\AgentChangeValue\\AgentChangeV.eps"
plot "G:\\workspace2\\stimulation\\AgentChangeValue\\NumOfAgent&Random2.txt" u 1:4 w lp pt 5 lw 2 ps 1.2 t "Random", '' u 1:3 w lp pt 7 lw 2 ps 1.2 t "OPT", '' u 1:2 w lp pt 9 lw 2 ps 1.2 t "TDM"
set output
 