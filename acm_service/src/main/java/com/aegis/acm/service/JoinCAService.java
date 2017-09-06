package com.aegis.acm.service;

import com.aegis.acm.domain.JoinCA;

public interface JoinCAService {
    JoinCA findByJid(Integer jid);

    void save(JoinCA model);

    void doAssociation(JoinCA model);

    void joinAction_deleteFromActivity(JoinCA model);
}
