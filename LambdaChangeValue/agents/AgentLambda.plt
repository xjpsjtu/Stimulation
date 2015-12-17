set terminal postscript enhanced
set term epscairo lw 2 font "Times-Roman, 26" size 5,4
set size ratio 0.8
set xrange [0:100]
set yrange [50:400]
set xtics 20
set ytics 100
set xlabel '{/Symbol l}' font "Times-Roman,26"
set ylabel "Value" font "Times-Roman,26"
set key at 100,400
set key box
set key width 1
set output "G:\\workspace2\\stimulation\\LambdaChangeValue\\agents\\AgentLambda.eps"
plot  "G:\\workspace2\\stimulation\\LambdaChangeValue\\agents\\LambdaChangeValue1-2.txt" u 1:2 w lp pt 5 lw 2 ps 1.2 t "NumofAgent = 400","G:\\workspace2\\stimulation\\LambdaChangeValue\\agents\\LambdaChangeValue2-2.txt" u 1:2 w lp pt 5 lw 2 ps 1.2 t "NumofAgent = 300","G:\\workspace2\\stimulation\\LambdaChangeValue\\agents\\LambdaChangeValue3-2.txt" u 1:2 w lp pt 5 lw 2 ps 1.2 t "NumofAgent = 250","G:\\workspace2\\stimulation\\LambdaChangeValue\\agents\\LambdaChangeValue4-2.txt" u 1:2 w lp pt 5 lw 2 ps 1.2 t "NumofAgent = 200" 
set output
 