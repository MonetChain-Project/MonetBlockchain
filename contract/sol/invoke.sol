pragma solidity ^0.4.0;
contract CA{
    uint public p;
    event e(address add,uint p);
    function fun(uint u1,uint u2){
        p = u1 + u2;
        e(msg.sender,p);
    }
}
contract CB{
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
}