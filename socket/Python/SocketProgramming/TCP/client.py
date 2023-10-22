import socket

from SocketProgramming.TCP.protocols import *

SERVER_IP = DEFAULT_IP
SERVER_PORT = DEFAULT_PORT


def communicate(client_socket):
    print("Communicating with server...")

    '''
    CODE GOES HERE
    '''

    print("Communication with server ended")


def main():
    try:
        print("1.Create socket")
        client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    except:
        print("Eroare la crearea socketului")
        return

    try:
        print("2.Connect")
        client_socket.connect((SERVER_IP, SERVER_PORT))

    except:
        print("Eroare la conectarea la server")
        client_socket.close()
        return

    try:

        communicate(client_socket)

    except:
        print("Eroare la I/O date")

    client_socket.close()


main()
