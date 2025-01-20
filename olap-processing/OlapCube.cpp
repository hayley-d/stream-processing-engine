#include <iostream>
#include <map>
#include <vector>

/*
* Dimension 1: Time (Temporal Dimension)
* Dimension 2: Event Type (Categorical Dimension)
* Dimension 3: Geographical (Temporal Dimension)
*/


struct Cube {
  std::map<std::tuple<int, int, int>, double> data;

    void add_data(int dim1, int dim2, int dim3, double value) {
        data[std::make_tuple(dim1,dim2,dim3)] = value;
    }

    double get_data(int dim1,int dim2, int dim3) {
        std::tuple<int,int,int> key = std::make_tuple(dim1,dim2,dim3);
        if (data.find(key) != data.end()) {
            return data[key];
        }
        return 0.0;
    }
};
