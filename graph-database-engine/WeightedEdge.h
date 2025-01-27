#ifndef WEIGHTEDEDGE_H
#define WEIGHTEDEDGE_H

#include "EdgeDecorator.h"
#include <memory>

class WeightedEdge : public EdgeDecorator {
private: 
    float weight;
public:
    WeightedEdge(std::shared_ptr<Edge> edge, float weight) : EdgeDecorator(edge), weight(weight) {}
    void describe() const override;
};
#endif //WEIGHTEDEDGE_H
