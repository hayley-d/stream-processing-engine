#include "Edge.h"

void Edge::add_version(int source, int destination, int version){
    this->versions.emplace_back(source,destination,version);
}

EdgeVersion* Edge::get_version(int version_id) {
    for (auto& version : versions) {
        if (version.version_id <= version_id && !version.is_deleted) {
            return &version;
        }
    }
    return nullptr;
}

void Edge::garbage_collect(int min_version) {
}

