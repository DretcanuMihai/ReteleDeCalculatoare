import socket
import time
from SocketProgramming.UDP.protocols import *

SERVER_IP = DEFAULT_IP
SERVER_PORT = DEFAULT_PORT

def serve(server_socket):
    print("Serving clients")
    sum = 0
    while (True):
        print("Waiting requests...")
        number, client_address = receive_int_ascii(server_socket)
        print("Received number:", number," from client:" ,client_address)
        send_number_ascii(server_socket,client_address,number)

def main():
    try:
        print("1.Create Socket")
        server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    except:
        print("Eroare la crearea de socket")
        return

    try:
        print("2.Bind Socket")
        server_socket.bind((SERVER_IP, SERVER_PORT))
    except:
        print("Eroare la bind-ul socketului")
        return

    try:
        serve(server_socket)
    except:
        print("Eroare la I/O date")

    server_socket.close()


main()
