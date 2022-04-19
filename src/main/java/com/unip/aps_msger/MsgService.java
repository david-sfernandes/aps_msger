package com.unip.aps_msger;

import com.unip.aps_msger.model.Message;
import com.unip.aps_msger.model.MessageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MsgService {
    @Autowired
    private MsgRepository repository;

    @Autowired
    private MsgMapper mapper;

    @Transactional
    public MessageDto save(MessageDto dto) {
        Message msg = mapper.toEntity(dto);
        repository.save(msg);
        MessageDto temp = mapper.toDto(msg);
        System.out.println("New message created to save { id:"
                + temp.getId() + ", msg: "
                + temp.getMsg() + " }");
        return temp;
    }
}
