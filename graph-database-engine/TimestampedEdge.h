#ifndef TIMESTAMPEDEDGE_H
#define TIMESTAMPEDEDGE_H

#include "EdgeDecorator.h"
#include <memory>
#include <string>


class  TimestampedEdge: public EdgeDecorator {
private: 
    std::string timestamp;
public:
    TimestampedEdge(std::shared_ptr<Edge> edge, std::string timestamp) : EdgeDecorator(edge), timestamp(timestamp) {}
    void describe() const override;
};
#endif //TIMESTAMPEDEDGE_H
