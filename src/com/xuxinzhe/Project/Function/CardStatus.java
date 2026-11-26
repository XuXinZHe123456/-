package com.xuxinzhe.Project.Function;
import com.xuxinzhe.Project.Class.CardState;

import javax.smartcardio.Card;

import static com.xuxinzhe.Project.Function.User.studentArrayList;

public class CardStatus
{
    public static void getFreezeCard()
    {
        CardState card = CardState.Freeze;
        System.out.println("目前卡状态：" + card.getDescription());
    }
}
