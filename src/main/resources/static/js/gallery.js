async function isAvailableURL(urlname)
{
    const result = await axios.get(`/galleryRest/urlname/${urlname}`)
    return result.data
}

async function isAvailableTitle(title)
{
    const result = await axios.get(`/galleryRest/title/${title}`)
    return result.data
}

async function getGalleryForAuto(keyword)
{
    const result = await axios.get(`/galleryRest/galleryList/${keyword}`)
    return result.data
}