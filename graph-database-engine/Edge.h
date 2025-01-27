#ifndef EDGE_H
#define EDGE_H

class Edge {
public:
    int source_node_id;
    int destination_node_id;

    Edge(int source, int dest): source_node_id(source), destination_node_id(dest) {};
    virtual void describe() const;
    virtual ~Edge() = default;
    
};

#endif //EDGE_H
