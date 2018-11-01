#use igraph for example
library(igraph)

dat=read.csv("matrix_adj.csv",header=TRUE,row.names=1,check.names=FALSE) # read .csv file
m=as.matrix(dat)

if ('-1' %in% m) {
  net=graph.adjacency(m,mode="directed",weighted=NULL,diag=FALSE) 
} else {
  net=graph.adjacency(m,mode="undirected",weighted=NULL,diag=FALSE) 
}

#import the sample_attributes file:
a=read.csv("matrix_color_v2.csv")
V(net)$Color=as.character(a$Color[match(V(net)$name,a$id)])
V(net)$color=V(net)$Color
V(net)$color=gsub("1","red",V(net)$color)
V(net)$color=gsub("2","cyan",V(net)$color)
V(net)$color=gsub("3","yellow",V(net)$color)
V(net)$color=gsub("4","purple",V(net)$color)
V(net)$color=gsub("5","green",V(net)$color)
V(net)$color=gsub("6","orange",V(net)$color)
V(net)$color=gsub("7","blue",V(net)$color)
V(net)$color=gsub("8","white",V(net)$color)
V(net)$color=gsub("9","grey",V(net)$color)
V(net)$color=gsub("10","black",V(net)$color)

plot.igraph(net,layout=layout.fruchterman.reingold, edge.color="black",edge.width=E(net)$weight)

#import the sample_attributes file:
a=read.csv("matrix_color_v3.csv")
V(net)$Color=as.character(a$Color[match(V(net)$name,a$id)])
V(net)$color=V(net)$Color
V(net)$color=gsub("1","red",V(net)$color)
V(net)$color=gsub("2","cyan",V(net)$color)
V(net)$color=gsub("3","yellow",V(net)$color)
V(net)$color=gsub("4","purple",V(net)$color)
V(net)$color=gsub("5","green",V(net)$color)
V(net)$color=gsub("6","orange",V(net)$color)
V(net)$color=gsub("7","blue",V(net)$color)
V(net)$color=gsub("8","white",V(net)$color)
V(net)$color=gsub("9","grey",V(net)$color)
V(net)$color=gsub("10","black",V(net)$color)

plot.igraph(net,layout=layout.fruchterman.reingold, edge.color="black",edge.width=E(net)$weight)
