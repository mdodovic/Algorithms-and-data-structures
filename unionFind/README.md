Union-find problem

There is a set of object represented by array of integers from 0 to number of objects (n). Main problem is to find if there is a path between two objects. There are several implementations (quick find, quick union, weighted quick union, and weighted quick union with path compression). Quick algorithms has O(N) time complexity while weighted algorithms has O(log(N)) time complexity.

Percolation

Given a porous landscape (black) with water (blue) on the surface, under what conditions will the water be able to drain through to the bottom? We model a percolation system using an n-by-n grid of sites. Each site is either open or blocked. A full site is an open site that can be connected to an open site in the top row via a chain of neighboring (left, right, up, down) open sites. We say the system percolates if there is a full site in the bottom row. In other words, a system percolates if we fill all open sites connected to the top row and that process fills some open site on the bottom row.

To estimate the percolation threshold (value p* such that when p > p* system cetrainly percolates, and for p < p* system almost never percolates) statistical Monte Carlo simulation is implemented.

![java symbol](https://user-images.githubusercontent.com/32821985/94189340-e51d0080-feaa-11ea-8522-9ff09097b4d5.png)
![eagle](https://user-images.githubusercontent.com/32821985/94189336-e4846a00-feaa-11ea-9798-9583882adfe6.png)
![jerry without tom](https://user-images.githubusercontent.com/32821985/94189342-e51d0080-feaa-11ea-86f7-32f376da2235.png)

