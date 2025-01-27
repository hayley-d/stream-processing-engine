#ifndef LABLEDEDGE_H
#define LABLEDEDGE_H
#include "EdgeDecorator.h"
#include <memory>
#include <string>

class LabeledEdge : public EdgeDecorator {
private: 
    std::string label;
public:
    LabeledEdge(std::shared_ptr<Edge> edge, std::string label) : EdgeDecorator(edge), label(label) {}
    void describe() const override;
};
#endif //LABLEDEDGE_H

