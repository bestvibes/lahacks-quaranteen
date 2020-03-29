from django.http import JsonResponse
from rest_framework.decorators import api_view


@api_view(['POST'])
def verifyApple(request):
    """
    inputs JSON with one field "image"
    output: JSON with one field "verified" either true or false
    """
    return JsonResponse({"verified": True}, status=201)


@api_view(['POST'])
def verifyVideo(request):
    """
    inputs JSON with one field "image"
    output: JSON with one field "verified" either true or false
    """
    return JsonResponse({"verified": True}, status=201)


@api_view(['POST'])
def verifyBook(request):
    """
    inputs JSON with one field "image"
    output: JSON with one field "verified" either true or false
    """
    return JsonResponse({"verified": True}, status=201)


@api_view(['POST'])
def verifySunrise(request):
    """
    inputs JSON with one field "image"
    output: JSON with one field "verified" either true or false
    """
    return JsonResponse({"verified": True}, status=201)
