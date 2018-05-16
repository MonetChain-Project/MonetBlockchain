pragma solidity ^0.4.0;
contract AddressContract{
    function AddressContract() payable {
        
    }
    
    function send(address add){
        uint u = 1 ether;
        add.transfer(u);
    }
}