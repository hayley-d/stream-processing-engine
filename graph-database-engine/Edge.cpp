#include "Edge.h"
#include <iostream>

void Edge::describe() const {
    std::cout << "Edge from " << this->source_node_id << this->destination_node_id << std::endl;
}
