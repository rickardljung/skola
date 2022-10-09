-------------------------------------------------------------------------------
-- Filename:        user_logic.vhd
-- Version:         v1.00c
-- Description:     The GCD functionallity of your choice
-------------------------------------------------------------------------------

library IEEE;
use IEEE.std_logic_1164.all;
use IEEE.std_logic_arith.all;
use IEEE.std_logic_unsigned.all;
use IEEE.numeric_std.all;
library Unisim;
use Unisim.all;

-----------------------------------------------------------------------------
-- Entity section
-----------------------------------------------------------------------------

entity user_logic is
  port (
    Clk      : in std_logic; -- clock
    Rst		 : in std_logic; -- reset, active high
    -- read signals
    Exists   : in std_logic; -- active if data is available
    Rd_ack   : out std_logic; -- read ack from this core
    D_in     : in std_logic_vector(0 to 31); -- data to this core
    -- write signals
    Full     : in std_logic; -- active if fifo is full
    Wr_en    : out std_logic; -- read ack from this core
    D_out    : out std_logic_vector(0 to 31) -- data from this core
  );
end entity user_logic;

architecture IMP of user_logic is
-------------------------------------------------------------------------------
-- Type declarations
-------------------------------------------------------------------------------
-------------------------------------------------------------------------------
-- Signal declarations
-------------------------------------------------------------------------------

type STATE_TYPE is (WAITING, READ, PROC1, PROC2, PROC3, PROC4, PROC5, PROC6, PROC7, WRITE);
signal CS, NS : STATE_TYPE;
signal CU, NU, CV, NV, Cshift, Nshift : std_logic_vector(0 to 31);
signal Cready, Nready : BIT;


begin

SYNC_PROC: process(Clk, Rst)
begin
	if (Rst = '1') then 
		CS <= WAITING;
-- other state variables reset
	elsif rising_edge(Clk) then
		CS <= NS;
		CU <= NU;
		CV <= NV;
		Cready <= Nready;
		Cshift <= Nshift;
		
-- other state variable assignment
	end if;
end process;
COMB_PROC: process(CS, Exists, Cready, D_in, CU, CV, Cshift, Full)
	variable t : std_logic_vector(0 to 31);

begin
	NV <= CV;
	NU <= CU;
	Nready <= Cready;
	Nshift <= Cshift;
	
	Rd_ack <= '0';
	Wr_en <= '0';
	D_out <= "00000000000000000000000000000000";
	
-- assign default signals here to avoid latches
	case CS is
		when WAITING =>
		Nshift <= "00000000000000000000000000000000";
		Rd_ack <= '0';
			if (Exists = '1') then
				NS <= READ;
			else
				NS <= WAITING;
			end if;			
			
		when READ =>
			if (Cready = '1') then
				NV <= D_in;
				NS <= PROC1;
				Nready <= '0';
			else 
				Nready <= '1';
				NU <= D_in;
				NS <= WAITING;
				
			end if;
			Rd_ack <= '1';
						
		when PROC1 =>
			Rd_ack <= '0';
			if (CU = 0) then
				NV <= CV;
				NS <= WRITE;
			elsif (CV = 0) then
				NV <= CU;
				NS <= WRITE;
			else
				NS <= PROC2;
			end if;
			Nshift <= "00000000000000000000000000000000";
		
		when PROC2 =>
			if (((CU or CV) and "00000000000000000000000000000001") = "00000000000000000000000000000000") then
				NU <= '0' & CU(0 to 30);
				NV <= '0' & CV(0 to 30);
				Nshift <= Cshift + "00000000000000000000000000000001";
				NS <= PROC2;
			else
				NS <=PROC3;
			end if;
			
		when PROC3 =>			
			if ((CU and "00000000000000000000000000000001") = "00000000000000000000000000000000") then
				NU <=  '0' & CU(0 to 30);
				NS <= PROC3;
			else
				NS <= PROC4;
			end if;

		when PROC4 =>			
			if ((CV and "00000000000000000000000000000001") = "00000000000000000000000000000000") then
				NV <= '0' & CV(0 to 30);
				NS <= PROC4;
			else
				if (CU > CV) then
					t := CV;
					NV <= CU;
					NU <= t; 
				end if;			
				NS <= PROC5;
			end if;		
	
		when PROC5 =>
			NV <= CV - CU;
			NS <= PROC6;

		when PROC6 =>							
			if (CV /= "00000000000000000000000000000000") then
				NS <= PROC4;				
			else			
				NS <= PROC7;						
			end if;

		when PROC7 =>
			if (Cshift /= "00000000000000000000000000000000") then
				NU <= CU(1 to 31) & '0';
				NS <= PROC7;
				Nshift <= Cshift - "00000000000000000000000000000001";
			else
				NS <= WRITE;
				NV <= CU;
			end if;

		when WRITE =>
			if (Full = '0') then
				D_out <= CV;	
				Wr_en <= '1';
				NS <= WAITING;
			else
				NS <= WRITE;
			end if;
			
			
-- assign outputs here
-- assign the next state depending on various conditions
-- have a 'when' for all states
	end case;
end process;



  --!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
  --  Observe!!! only use the type std_logic_vector(0 to 31) for integers.
  --  The bit ordered of the vector must be 0 to 31, 31 downto 0 will not work!
  --!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	

end   architecture IMP;