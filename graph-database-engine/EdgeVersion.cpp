#include "EdgeVersion.h"
#include <iostream>

void EdgeVersion::describe() const {
    std::cout << "Edge from " << this->source_node_id << this->destination_node_id << std::endl;
}
