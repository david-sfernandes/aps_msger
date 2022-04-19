package com.unip.aps_msger;

import com.unip.aps_msger.model.Message;
import com.unip.aps_msger.model.MessageDto;
import org.springframework.stereotype.Component;

@Component
public class MsgMapper {
    public Message toEntity(MessageDto dto) {
        Message msg = new Message();
        msg.setId(dto.getId());
        msg.setMsg(dto.getMsg());
        return msg;
    }

    public MessageDto toDto(Message msg) {
        return new MessageDto(msg.getId(), msg.getMsg());
    }
}
