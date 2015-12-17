set terminal postscript enhanced
set term epscairo lw 2 font "Times-Roman, 26" size 5,4
set size ratio 0.8
set xrange [0:100]
set yrange [0:600]
set xtics 20
set ytics 200
set xlabel '{/Symbol l}' font "Times-Roman,26"
set ylabel "Value" font "Times-Roman,26"
set key at 100,600
set key box
set key width 1
set output "G:\\workspace2\\stimulation\\LambdaChangeValue\\budget\\BudgetLambda.eps"
plot  "G:\\workspace2\\stimulation\\LambdaChangeValue\\budget\\LambdaChangeValue1-2.txt" u 1:2 w lp pt 5 lw 2 ps 1.2 t "Budget = 15000","G:\\workspace2\\stimulation\\LambdaChangeValue\\budget\\LambdaChangeValue2-2.txt" u 1:2 w lp pt 5 lw 2 ps 1.2 t "Budget = 10000","G:\\workspace2\\stimulation\\LambdaChangeValue\\budget\\LambdaChangeValue3-2.txt" u 1:2 w lp pt 5 lw 2 ps 1.2 t "Budget = 8000","G:\\workspace2\\stimulation\\LambdaChangeValue\\budget\\LambdaChangeValue4-2.txt" u 1:2 w lp pt 5 lw 2 ps 1.2 t "Budget = 6000" 
set output
 