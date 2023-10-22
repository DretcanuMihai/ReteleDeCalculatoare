import socket
from SocketProgramming.UDP.protocols import *

SERVER_IP = DEFAULT_IP
SERVER_PORT = DEFAULT_PORT

def communicate(client_socket):
    print("My address is:",client_socket.getsockname())
    print("Communicating with server...")

    send_number_ascii(client_socket,(SERVER_IP,SERVER_PORT),10)
    print("My address is:",client_socket.getsockname())

    my_sum, server_address = receive_int_ascii(client_socket)
    print("My address is:", client_socket.getsockname())

    print("Communication with server ended")

def main():
    try:
        print("1.Create Socket")
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    except:
        print("Eroare la crearea de socket")
        return

    try:
        communicate(client_socket)

    except:
        print("Eroare la I/O date")

    client_socket.close()

main()
