pragma solidity ^0.4.0;

contract modifierContract {
    address public owner;
    uint public u;
    
    function modifierContract(){
        owner = msg.sender;
    }
    
    modifier onlyOwner{
        if(msg.sender !=owner ){
            throw;
        }else{
            _;
        }
    }
    
    function set(uint _u) onlyOwner{
        u = _u;
    }
}