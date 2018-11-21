#use igraph for example
library(igraph)

dat=read.csv(file.choose(),header=TRUE,row.names=1,check.names=FALSE) # read .csv file
m=as.matrix(dat)

if ('-1' %in% m) {
  net=graph.adjacency(m,mode="directed",weighted=NULL,diag=FALSE)
} else {
  net=graph.adjacency(m,mode="undirected",weighted=NULL,diag=FALSE) 
}
net=graph.adjacency(m,mode="directed",weighted=NULL,diag=FALSE)

#import the sample_attributes file:
a=read.csv(file.choose())

plot.igraph(net,layout=layout.fruchterman.reingold, vertex.label=a$Order, edge.color="black")