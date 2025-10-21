package com.finflow.arisan_digital.infra.db.repository;

import com.finflow.arisan_digital.infra.db.entity.ArisanGroupEntity;
import java.util.Optional;

public interface ArisanGroupRepositoryCustom {
    // Ini method kustom yang akan kita implementasikan di kelas Impl
    Optional<ArisanGroupEntity> findByNameUsingCustomQuery(String name);
}
