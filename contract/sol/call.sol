pragma solidity ^0.4.0;
contract CC1{
    uint public p;
    bytes public failmsg;
    string public str;
    event e(address add,uint p);
    event e1(address add,bytes b);
    
    function CC1(string _str){
        str = _str;
    }
    function fun(uint u1,uint u2){
        p = u1 + u2;
        e(msg.sender,p);
    }
    
    function (){
        failmsg = msg.data;
        e1(msg.sender,failmsg);
    }
}
contract CD1{
    uint public q;
    bool public b;
    
    function call1(address add) returns(bool) {
        b = add.call(bytes4(keccak256("fun(uint256,uint256)")),2,3);
        return b;
    }
    
    function call2(address add) returns(bool){
        b = add.delegatecall(bytes4(keccak256("fun(uint256,uint256)")),1,2);
        return b;
    }
    
    function call3(address add) returns(bool){
        b = false;
        b = add.call("aaaa",256,100,100);
        return b;
    }
    function call4(address add) returns(bool){
        b = false;
        b = add.delegatecall("bbbb",123,"10x0",100);
        return b;
    }
}