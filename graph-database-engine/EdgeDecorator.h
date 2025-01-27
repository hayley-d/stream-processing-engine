#ifndef EDGEDECORATOR_H
#define EDGEDECORATOR_H

#include "Edge.h"
#include <memory>

class EdgeDecorator : public Edge {
protected:
    std::shared_ptr<Edge> wrappedEdge; // The edge being decorated
public:
    EdgeDecorator(std::shared_ptr<Edge> edge) : Edge(edge->source_node_id,edge->destination_node_id) {
        this-> wrappedEdge = edge;
    }
    virtual void describe() const override;
    virtual ~EdgeDecorator() = default;
};
#endif //EDGEDECORATOR_H
