pragma solidity ^0.4.0;

contract C1 {
    uint public data = 100;
    function test(string _s) external returns(bytes32){
        return keccak256(_s);
    }
    
    function test2() public returns(uint){
        return 10;
    }
}



contract C2{
    bytes32 public b32;
    uint public u;
    uint public kdata;
    
    C1 c1 = new C1();
    
    function test() returns(bytes32){
        b32 = c1.test("a");
        kdata = c1.data();
        return b32;
    }
    
    function test2() {
        u = c1.test2();
    }
}

