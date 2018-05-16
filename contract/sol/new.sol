pragma solidity ^0.4.0;

contract D {
    uint public x;
    uint public amount;
    
    
    function D(uint _a) payable{
        x = _a;
        amount = msg.value;
    } 
}


contract E {
    event e(uint x,uint amount);
    
    D d = new D(4);
    
    function E(uint _u) payable{
        e(d.x(),d.amount());
        D d1 = new D(_u);
        e(d1.x(),d1.amount());
    }
    
    function creatD(uint _x,uint _amount){
        D d2 = (new D).value(_amount)(_x);
        e(d2.x(),d2.amount());
    }
}

