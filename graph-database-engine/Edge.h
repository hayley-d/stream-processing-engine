#ifndef EDGE_H
#define EDGE_H

#include "EdgeVersion.h"
#include <memory>
#include <vector> 

class Edge {
public:
    std::vector<EdgeVersion> versions;

    Edge()=default;
    virtual void describe() const;
    void add_version(int source, int destination, int version);
    EdgeVersion* get_version(int version_id);
    void garbage_collect(int min_version);
    virtual ~Edge() = default;
    
};

#endif //EDGE_H
