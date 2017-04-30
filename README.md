# mpvga-gen
_Monotone Polygon and Vertex Guard Approximation Generator_

This program, written in java, will generate a random monotone polygon with a set amount of vertices.
Then, it will take that polygon and generate a set of linear program constraints that satisfies the vertex guard problem.
Using this, it calls glpsol (included for convenience) to solve the linear program.
The program then examines the output to see whether or not a vertex was assigned a value less than a specified value.

This program was developed as part of my Honors Thesis project at the University of Wisconsin Oshkosh, titled _"Approximating Minimum Vertex Guards of Monotone Polygons Using Linear Programming"_, in Spring of 2017.
