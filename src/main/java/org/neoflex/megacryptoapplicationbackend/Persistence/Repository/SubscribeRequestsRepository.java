package org.neoflex.megacryptoapplicationbackend.Persistence.Repository;

import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.Embeddable.RequestId;
import org.neoflex.megacryptoapplicationbackend.Persistence.Entity.SubscribeRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscribeRequestsRepository extends JpaRepository<SubscribeRequest, RequestId> {
    public List<SubscribeRequest> findAllByIdUsername(String username);
}
