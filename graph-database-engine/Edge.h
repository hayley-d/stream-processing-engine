#ifndef EDGE_H
#define EDGE_H

class Edge {
    int source_node_id;
    int destination_node_id;
public:
    Edge(int source, int dest): source_node_id(source), destination_node_id(dest) {};
    void describe() const;
    ~Edge() = default;
    
};

#endif //EDGE_H
