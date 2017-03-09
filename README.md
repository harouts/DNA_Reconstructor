#DNA Reconstructor

This application uses the methods for reconstructing DNA sequences from it's l-mer fragments, as demonstrated here in the sequencing by hybridization section: http://veda.cs.uiuc.edu/courses/fa09/cs466/lectures/Lecture9.pdf

In summary, fragmenting a dna sequence into it's l-mer composition, creating an Eulerian graph representation and, finding an euler path in the graph is equivalent to finding the original DNA sequence. This may be useful in assembling fragmented DNA, or re-assembling a desired sequence from a pool of fragments.