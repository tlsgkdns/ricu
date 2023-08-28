async function uploadToServer(formObj)
{
    console.log("upload to server........")
    console.log(formObj)

    const response = await axios(
        {
            method: 'post',
            url: '/upload',
            data: formObj,
            headers: {
                'Content-Type': 'multipart/form-data',
            },
        }
    );
    return response.data;
}

async function removeFileToServer(uuid, fileName)
{
    const response = await axios.delete(`/remove/${uuid}_${fileName}`)

    return response.data
}

async function removeFileLinkToServer(link)
{
    const response = await axios.delete(`/remove/${link}`)

    return response.data
}