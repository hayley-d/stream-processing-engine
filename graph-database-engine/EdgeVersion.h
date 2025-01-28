#ifndef EDGEVERSION_H
#define EDGEVERSION_H

class EdgeVersion {
public:
    int source_node_id;
    int destination_node_id;
    int version_id;
    bool is_deleted;

    EdgeVersion(int source, int dest,int version, bool deleted = false): source_node_id(source), destination_node_id(dest), version_id(version), is_deleted(deleted) {};
    virtual void describe() const;
    virtual ~EdgeVersion() = default;
};

#endif //EDGEVERSION_H
